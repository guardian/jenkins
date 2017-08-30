export
  { fromEvent
  };

import { Morphism } from './types';
import { Signal, constant } from './signal';

const fromEvent = <A>(e: string, t: EventTarget) => (z: A) => (f: Morphism<Event, A>): Signal<A> => {
  const s: Signal<A> = constant(z); 
  t.addEventListener(e, (ev) => {
    s.set(f(ev));
  });
  return s;
}