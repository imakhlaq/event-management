"use client";

import {CalenderEvent} from "../../../../types";

type Props = { calendarEvent: CalenderEvent, };

export default function OneEvent({calendarEvent}: Props) {
    return <div className="flex">
        <p>{calendarEvent.summary}</p>
        <p>{calendarEvent.start.dateTime.timeZoneShift}</p>
        <p>{calendarEvent.end.dateTime.timeZoneShift}</p>
    </div>;
};
