enum Product {
  CONTRIBUTION          = 1,
  RECURRING_CONTRIBUTION= 2,
  MEMBERSHIP_SUPPORTER  = 3,
  MEMBERSHIP_PATRON     = 4,
  MEMBERSHIP_PARTNER    = 5,
  DIGITAL_SUBSCRIPTION  = 6,
  PRINT_SUBSCRIPTION    = 11
};

enum ComponentType {
  READERS_QUESTIONS_ATOM        = 1, 
  QANDA_ATOM                    = 2,
  PROFILE_ATOM                  = 3,
  GUIDE_ATOM                    = 4,
  TIMELINE_ATOM                 = 5,
  NEWSLETTER_SUBSCRIPTION       = 6,
  SURVEYS_QUESTIONS             = 7,
  ACQUISITIONS_EPIC             = 8,
  ACQUISITIONS_ENGAGEMENT_BANNER= 9,
  ACQUISITIONS_THANK_YOU_EPIC   = 10, 
  ACQUISITIONS_HEADER           = 11, 
  ACQUISITIONS_FOOTER           = 12, 
  ACQUISITIONS_INTERACTIVE_SLICE= 13, 
  ACQUISITIONS_NUGGET           = 14, 
  ACQUISITIONS_STANDFIRST       = 15, 
  ACQUISITIONS_THRASHER         = 16, 
  ACQUISITIONS_EDITORIAL_LINK   = 17   
};

type ComponentV2 = {
  componentType : ComponentType;
  id           ?: string;
  products      : Set<Product>;
  campaignCode ?: string;
  labels        : Set<string>;
}

enum Action {
  INSERT    = 1,
  VIEW      = 2,
  EXPAND    = 3,
  LIKE      = 4,
  DISLIKE   = 5,
  SUBSCRIBE = 6,
  ANSWER    = 7,
  VOTE      = 8,
  CLICK     = 9
}

type AbTest = {
  name          : string;
  variant       : string;
  complete     ?: boolean;
  campaignCodes?: Set<string>;
};

type ComponentEvent = {
  component : ComponentV2;
  action    : Action;
  value    ?: string;
  id       ?: string;
  abTest   ?: AbTest;
};

type OphanRecord = {
  componentEvent: ComponentEvent
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