export type Coordinate = [number, number];

export type ActivityType =
  | "Event"
  | "Conference"
  | "Workshop"
  | "Festival"
  | "Meeting"
  | "Party"
  | "Other";

export type Activity = {
  name: string;
  description: string;
  createdAt: Date;
  type: ActivityType;
  position: Coordinate;
};
