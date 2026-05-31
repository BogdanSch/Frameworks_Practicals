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
  activityDate: string;
  createdAt: string;
  type: ActivityType;
  position: Coordinate;
};
