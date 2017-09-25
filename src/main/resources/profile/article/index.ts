/// <reference path="../../typedefs.ts" />

import { Channel, map, filter, take, tap } from '../../channels';
import { fromEvent } from '../../events';

type Atom = {
  atomId: string,
  start: (a: Atom) => Promise<void>;
  stop: () => void;
};

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
    let chan: Channel<Action>;

    const start = (a: ProfileAtom): Promise<void> => {
      chan = fromEvent('click', a.question)
      ['->'] (filter((e: UIEvent) => (e.target as Element).classList.contains('atom__button')))
      ['->'] (map((e: UIEvent) => (e.target as HTMLButtonElement).value === 'like' ? Action.LIKE : Action.DISLIKE))
      ['->'] (take(1));
      tap(onFeedback(a))(chan);
      return Promise.resolve();
    };
    
    const stop = () => {
      chan.close();
    };
    
    const onFeedback = (p: ProfileAtom) => (a: Action): void => {
      const emptySet = new Set();
      ophan.record({
        componentEvent: {
          component: {
            componentType: ComponentType.PROFILE_ATOM,
            id: p.snippetId,
            products: emptySet,
            labels: emptySet
          },
          action: a
        }
      });
      dom.write(() => {
        p.ack.hidden = false;
        p.question.hidden = true;
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
  