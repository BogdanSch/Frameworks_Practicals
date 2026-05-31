import { type SubmitEvent, useContext, useEffect, useState } from "react";
import { ActivityContext, CoordinateContext } from "../contexts";
import type { Activity, ActivityType } from "../types";
import { Modal } from "react-bootstrap";

const allActivityTypes: ActivityType[] = [
  "Event",
  "Conference",
  "Workshop",
  "Festival",
  "Meeting",
  "Party",
  "Other",
];

export default function ActivityForm() {
  const { addActivity } = useContext(ActivityContext);
  const { coordinate, setCoordinate } = useContext(CoordinateContext);

  const [show, setShow] = useState<boolean>(false);
  const [formData, setFormData] = useState<Activity>({
    name: "",
    description: "",
    activityDate: "",
    createdAt: "",
    type: "Event",
    position: [0, 0],
  });

  const handleOpen = () => setShow(true);
  const handleClose = () => setShow(false);

  const handleSubmit = (e: SubmitEvent<HTMLFormElement>) => {
    e.preventDefault();

    const activity: Activity = formData;
    activity.position = coordinate || [0, 0];

    addActivity(activity);
    handleClose();
    setCoordinate(null);
  };

  const handleChange = (
    e: React.ChangeEvent<
      HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement
    >,
  ) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  useEffect(() => {
    if (coordinate) {
      handleOpen();
    }
  }, [coordinate]);

  return (
    <Modal
      className="modal"
      tabIndex={-1}
      id="activityModal"
      show={show}
      onHide={handleClose}
    >
      <Modal.Header closeButton>
        <Modal.Title>New Activity</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <form className="mb-2" onSubmit={handleSubmit} id="createActivityForm">
          <div className="mb-3">
            <label htmlFor="activityName" className="form-label">
              Activity Name*
            </label>
            <input
              type="text"
              className="form-control"
              id="activityName"
              name="name"
              onChange={handleChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="activityDescription" className="form-label">
              Activity Description*
            </label>
            <textarea
              className="form-control"
              id="activityDescription"
              name="description"
              onChange={handleChange}
              rows={4}
              required
            ></textarea>
          </div>
          <div className="mb-3">
            <label htmlFor="activityDate" className="form-label">
              Activity Date*
            </label>
            <input
              type="date"
              className="form-control"
              id="activityDate"
              name="activityDate"
              onChange={handleChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="activityType" className="form-label">
              Activity Type*
            </label>
            <select
              className="form-select"
              id="activityType"
              name="type"
              onChange={handleChange}
              required
            >
              {allActivityTypes.map((type) => (
                <option value={type} key={type}>
                  {type}
                </option>
              ))}
            </select>
          </div>
          <div id="requiredFieldsHelp" className="form-text">
            All fields marked with (*) are required to be filled in.
          </div>
        </form>
      </Modal.Body>
      <Modal.Footer>
        <button
          type="button"
          className="btn btn-secondary"
          data-bs-dismiss="modal"
          onClick={handleClose}
        >
          Close
        </button>
        <button
          type="submit"
          className="btn btn-primary"
          form="createActivityForm"
        >
          Save changes <i className="bi bi-bookmark-check"></i>
        </button>
      </Modal.Footer>
    </Modal>
  );
}
