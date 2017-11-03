//@flow

type Channel<A> = { put         : (A) => PutResult<A>
                  , take        : () => TakeResult<A>
                  , close       : () => void
                  , isClosed    : boolean
                  , putAsync    : (A) => Promise<boolean>
                  , takeAsync   : () => Promise<A | null>
                  , map         : <B>(A => B) => Channel<B>
                  , filter      : (A => boolean) => Channel<A>
                  , apply       : <B>(Channel<(A) => B>) => Channel<B>
                  , merge       : (Channel<A>) => Channel<A>
                  , dropRepeats : (([A,A]) => boolean) => Channel<A>
                  , takeN       : (number) => Channel<A>
                  , dropN       : (number) => Channel<A>
                  , tap         : (A => void) => void
                  };

type ChannelOp<A> = ['resume' | 'park', A];

type PutResult<A> = ChannelOp<boolean>
                  | ChannelOp<A | null>;

type TakeResult<A> = ChannelOp<A | null>
                   | ChannelOp<null>;

const chan = <A>(onClose?: () => void): Channel<A> => {
  let isClosed = false;
  let val: A | null;

  const take = (): TakeResult<A> => {
    if( canTake() ) {
      const ret: A = (val: any);
      val = null;
      return ['resume', ret];
    } else if( isClosed ) {
      return ['resume', null];
    } else {
      return ['park', null];
    }
  }

  const put = (a: A): PutResult<A> => {
    if( isClosed ) {
      return ['resume', false];
    } else if( canPut() ) {
      val = a;
      return ['resume', true];
    } else {
      return ['park', null];
    }
  };

  const canPut = (): boolean => true;
  const canTake = (): boolean => !!val;

  const close = (): void => { 
    if (!isClosed) {
      if (onClose) {
        onClose();
      }
      isClosed = true;
    }
  };


const putAsync = (a: A): Promise<boolean> =>
  new Promise(resolve => {
    const tryPut = () => {
      const res: PutResult<A> = put(a);
      if (res[0] === 'resume')
        resolve((res: any)[1]);
      else
        setTimeout(tryPut, 0);
    }
    tryPut();
  });

  const takeAsync = (): Promise<A | null> =>
    new Promise(resolve => {
      const tryTake = () => {
        const res: TakeResult<A> = take();
        if( res[0] === 'resume' )
          resolve((res: any)[1]);
        else
          setTimeout(tryTake, 0);
      }
      tryTake();
    });

  // Maps incoming Channel to a new one
  const map = <B>(f: A => B): Channel<B> => {
    const cb: Channel<B> = chan();
    const map_rec = () => {
      takeAsync().then(a => {
        if (a === null)
          cb.close();
        else {
          cb.put(f(a));
          map_rec();
        }
      });
    }
    map_rec();
    return cb;
  }

  // Filters incoming Channel.
  // Note: requires a zero should the current value not pass the test
  const filter = (f: (A) => boolean): Channel<A> => {
    const ca: Channel<A> = chan();
    const filter_rec = () => {
      takeAsync().then(a => {
        if (a === null)
          ca.close();
        else {
          if (f(a)) ca.put(a);
          filter_rec();
        }
      });
    }
    filter_rec();
    return ca;
  }

  // Maps incoming functions from one Channel to incoming values of another
  const apply = <B>(cfa: Channel<(A) => B>): Channel<B> => {
    const cb: Channel<B> = chan();
    const apply_rec = () => {
      Promise.all([
        cfa.takeAsync(),
        takeAsync()
      ]).then(([f, a]) => {
        if (f === null || a === null)
          cb.close();
        else {
          cb.put(f(a));
          apply_rec();
        }
      });
    }
    apply_rec();
    return cb;
  }

  // Merges two Channels together
  const merge = (ca: Channel<A>): Channel<A> => {
    const ca3: Channel<A> = chan();
    const merge_rec = (ca: Channel<A>) => {
      ca.takeAsync().then(a => {
        if (a === null)
          null
        else {
          ca3.put(a);
          merge_rec(ca);
        }
      });
    }
    merge_rec(c);
    merge_rec(ca);
    return ca3;
  }

  // Outputs incoming values so long as they are distinct from one to the next
  const dropRepeats = (eq: ([A,A]) => boolean = strictEq): Channel<A> => {
    const ca: Channel<A> = chan();
    let aa: A;
    const drop_rec = () => {
      takeAsync().then(a => {
        if (a === null)
          ca.close();
        else {
          if (!eq([a, aa]))
            ca.put(a);
          drop_rec();
        }
      });
    }
    drop_rec();
    return ca;
  }

  const takeN = (n: number): Channel<A> => {
    const ca: Channel<A> = chan();
    const take_rec = (n: number) => {
      if( n === 0 )
        ca.close();
      else
        takeAsync().then(a => {
          if( a === null )
            ca.close();
          else {
            ca.put(a);
            take_rec(n - 1);
          }
        });
    }
    take_rec(n);
    return ca;
  }

  const dropN = (n: number): Channel<A> => {
    const ca: Channel<A> = chan();
    const drop_rec = (n: number) => {
      takeAsync().then(a => {
        if (a === null)
          ca.close();
        else {
          if (n === 0) {
            ca.put(a);
            drop_rec(0);
          } else
            drop_rec(n - 1);
        }
      });
    }
    drop_rec(n);
    return ca;
  }

  const tap = (f: (A) => void): void => {
    const tap_rec = () => {
      takeAsync().then(a => {
        if (a === null)
          return;
        f(a);
        tap_rec();
      });
    }
    tap_rec();
  }

  const c: Channel<A> = Object.freeze({
    put,
    take,
    putAsync, 
    takeAsync,
    close,
    isClosed,
    map, 
    filter, 
    apply, 
    merge, 
    dropRepeats, 
    takeN, 
    dropN, 
    tap
  });

  return c;
}

const strictEq = <A>([a1, a2]: [A, A]): boolean => a1 === a2;


export type {
  Channel
};

export {
  chan, 
};
