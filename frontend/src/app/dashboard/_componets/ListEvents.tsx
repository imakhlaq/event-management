import {CalenderEvents} from "../../../../types";
import OneEvent from "@/app/dashboard/_componets/Event";

type Props = {
    calendarEvents: CalenderEvents
};
export default function ListEvents({calendarEvents}: Props) {
    return (
        <div className="">
            {calendarEvents.map(event => (
                <OneEvent key={event.id} calendarEvent={event}/>
            ))}
        </div>
    );
};
