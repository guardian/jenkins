interface OphanRecord {
  [propName: string]: number | string | boolean
};
  
type OphanService = {
  record: (x: OphanRecord) => never
};

type DomService = {
  write: (f: () => void) => Promise<void>
  read: <A>(f: () => A) => Promise<A>
}
  
type Services = {
  ophan: OphanService,
  dom: DomService
};

type Success<A> = A;

type Failure = string;

type Try<A> = Success<A> | Failure;

type Coeval<A> = {
  runTry: () => Try<A>
};