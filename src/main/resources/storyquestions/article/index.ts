interface OphanRecord {
  [propName: string]: number | string | boolean
};

type OphanService = {
  record: (x: OphanRecord) => never
};

type Services = {
  ophan: OphanService
};

export default function({ ophan }: Services) {
  const secretNumber = Math.random() * 123456789

  ophan.record({
    secretNumber: secretNumber
  });
}
