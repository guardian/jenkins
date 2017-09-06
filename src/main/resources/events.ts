export
  { fromEvent
  };

import { Channel, chan, putAsync } from './channels';

const fromEvent = (e: string, t: EventTarget): Channel<Event> => {
  const c: Channel<Event> = chan(); 
  t.addEventListener(e, (ev: Event) => {
    putAsync(ev)(c);
  });
  return c;
}