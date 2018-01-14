
export class Trade {
  private customer: string;
  private ccyPair: string;
  private type: string;
  private style: string;
  private direction: string;
  private strategy: string;
  private tradeDate: string;
  private amount1: number;
  private amount2: number;
  private rate: number;
  private valueDate: string;
  private deliveryDate: string;
  private expiryDate: string;
  private trader: string;
  private payCcy: string;
  private excerciseStartDate: string;
  private premium: number;
  private premiumCcy: string;
  private premiumType: string;
  private premiumDate: string;
  private legalEntity: string;

  constructor(customer: string, ccyPair: string, type: string, style: string, direction: string, strategy: string, tradeDate: string,
    amount1: number, amount2: number, rate: number, valueDate: string, deliveryDate: string, expiryDate: string, trader: string,
    payCcy: string, excerciseStartDate: string, premium: number, premiumCcy: string, premiumType: string, premiumDate: string, legalEntity: string) {
    this.customer = customer;
    this.ccyPair = ccyPair;
    this.type = type;
    this.style = style;
    this.direction = direction;
    this.strategy = strategy;
    this.tradeDate = tradeDate;
    this.amount1 = amount1;
    this.amount2 = amount2;
    this.rate = rate;
    this.valueDate = valueDate;
    this.deliveryDate = deliveryDate;
    this.expiryDate = expiryDate;
    this.trader = trader;
    this.payCcy = payCcy;
    this.excerciseStartDate = excerciseStartDate;
    this.premium = premium;
    this.premiumCcy = premiumCcy;
    this.premiumType = premiumType;
    this.premiumDate = premiumDate;
    this.legalEntity = legalEntity;
  }

}
