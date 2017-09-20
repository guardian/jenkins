/// <reference path="../../typedefs.ts" />

import { Channel, map, filter, take, tap } from '../../channels';
import { fromEvent } from '../../events';

type Atom = {
  atomId: string,
  start: (a: Atom) => Promise<void>;
  stop: () => void;
};

enum Feedback { Like = 'like', Dislike = 'dislike' };

type Snippet = {
  snippetId: string;
  snippetType: string;
};

type ProfileAtom = {
  question: HTMLElement;
  ack: HTMLElement;
} & Snippet & Atom;

export default function({ ophan, dom }: Services) {
  const AtomBuilder = (root: Element): Coeval<ProfileAtom> => {
    let chan: Channel<Feedback>;

    const start = (a: ProfileAtom): Promise<void> => {
      chan = fromEvent('click', a.question)
      ['->'] (filter((e: UIEvent) => (e.target as Element).classList.contains('atom__button')))
      ['->'] (map((e: UIEvent) => (e.target as HTMLButtonElement).value === 'like' ? Feedback.Like : Feedback.Dislike))
      ['->'] (take(1));
      tap(onFeedback(a))(chan);
      return Promise.resolve();
    };
    
    const stop = () => {
      chan.close();
    };
    
    const onFeedback = (a: ProfileAtom) => (x: Feedback): void => {
      ophan.record({
        atomId: a.snippetId,
        component: `snippet_${a.snippetType}`,
        value: `${a.snippetType}_feedback_${x}`
      });
      dom.write(() => {
        a.ack.hidden = false;
        a.question.hidden = true;
      });
    }
    
    const runTry = (): Try<ProfileAtom> => {
      const question = (root.querySelector('.atom--snippet__feedback') as HTMLElement);
      const ack = (root.querySelector('.atom--snippet__ack') as HTMLElement);
      const snippet = (root.querySelector('.atom--snippet') as HTMLElement);

      return question && ack && snippet
        ? Object.freeze({
          atomId: root.id,
          snippetId: <string>snippet.dataset.snippetId,
          snippetType: <string>snippet.dataset.snippetType,
          question,
          ack,
          stop,
          start(): Promise<void> {
            return start(this);
          }
        })
        : 'Some elements were missing when initialising atom';
    }

    return Object.freeze({ runTry });
  }

  return AtomBuilder;
}
  