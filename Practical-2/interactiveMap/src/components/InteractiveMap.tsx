import { MapContainer, TileLayer } from "react-leaflet";
import EventMarkers from "./ActivityMarkers";

export default function InteractiveMap() {
  return (
    <MapContainer
      center={[49.98, 36.23]}
      zoom={13}
      maxZoom={20}
      scrollWheelZoom={false}
      id="map"
    >
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      <EventMarkers />
    </MapContainer>
  );
}
