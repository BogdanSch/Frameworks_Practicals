import { createContext, type FC, type ReactNode } from "react";
import { useLocalStorage } from "../hooks";
import type { Activity } from "../types";

type ActivityContextData = ReturnType<typeof useProvideActivity>;
export const ActivityContext = createContext<ActivityContextData>(
  {} as ActivityContextData,
);

export const useProvideActivity = () => {
  const [activities, setActivities, clearActivities] = useLocalStorage<
    Activity[]
  >("activities", []);

  const addActivity = (activity: Activity) => {
    setActivities([...activities, activity]);
  };

  return { activities, addActivity, clearActivities };
};

type ActivityProviderProps = {
  children: ReactNode;
};
export const ActivityProvider: FC<ActivityProviderProps> = ({ children }) => {
  const activity = useProvideActivity();
  return (
    <ActivityContext.Provider value={activity}>
      {children}
    </ActivityContext.Provider>
  );
};
