export interface IForecast {
  averageTemperature: number;
  temperatureUnit: string;
  precipitationProbability: IPrecipitationProbability[];
  date: Date;
}

export interface IPrecipitationProbability {
  probability: number;
  period: string;
}
