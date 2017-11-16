//@flow
import type { Channel } from './channels';
import { chan } from './channels';

const fromEvent = (e: string, t: EventTarget): Channel<Event> => {
  const h = (ev: Event) => {
    c.putAsync(ev);
  };
  const c: Channel<Event> = chan(() => {
    t.removeEventListener(e, h);
  }); 
  t.addEventListener(e, h);
  return c;
}

export {
  fromEvent
};