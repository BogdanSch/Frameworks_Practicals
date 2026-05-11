import { createContext, useState, type FC, type ReactNode } from "react";
import type { Coordinate } from "../types";

type CoordinateContextData = ReturnType<typeof useProvideCoordinate>;
export const CoordinateContext = createContext<CoordinateContextData>(
  {} as CoordinateContextData,
);

export const useProvideCoordinate = () => {
  const [coordinate, setCoordinate] = useState<Coordinate | null>(null);
  return { coordinate, setCoordinate };
};

type CoordinateProviderProps = {
  children: ReactNode;
};
export const CoordinateProvider: FC<CoordinateProviderProps> = ({
  children,
}) => {
  const coordinate = useProvideCoordinate();
  return (
    <CoordinateContext.Provider value={coordinate}>
      {children}
    </CoordinateContext.Provider>
  );
};
