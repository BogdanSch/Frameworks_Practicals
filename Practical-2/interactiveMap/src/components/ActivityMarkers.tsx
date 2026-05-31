import { useContext } from "react";
import { useMapEvents, Marker, Popup } from "react-leaflet";
import type { Coordinate } from "../types";
import { ActivityContext, CoordinateContext } from "../contexts/";

export default function ActivityMarkers() {
  const { activities } = useContext(ActivityContext);
  const { setCoordinate } = useContext(CoordinateContext);

  useMapEvents({
    click(e) {
      const position: Coordinate = [e.latlng.lat, e.latlng.lng];
      setCoordinate(position);
    },
  });

  return (
    <>
      {activities.map((activity, index) => (
        <Marker position={activity.position} key={index}>
          <Popup>
            <h4>{activity.name}</h4>
            <p>{activity.description}</p>
            <p>{activity.activityDate}</p>
            <p>{activity.type}</p>
          </Popup>
        </Marker>
      ))}
    </>
  );
}
