"use client"
import {CalenderEvent} from "../../../../../types";
import {Input} from "@/components/ui/input";
import DateTimePicker from "@/app/edit-event/[id]/_components/DateTimePicker";
import {Textarea} from "@/components/ui/textarea";
import {Button} from "@/components/ui/button";
import {useEffect, useState} from "react";
import dayjs, {Dayjs} from "dayjs";

type Props = { event?: CalenderEvent };
export default function EventEditor({event}: Props) {

    const [eventStartTime, setEventStartTime] = useState<Dayjs | null>(null)
    const [eventEndTime, setEventEndTime] = useState<Dayjs | null>(null)
    const [summary, setSummary] = useState<string | undefined>(undefined)
    const [description, setDescription] = useState<string | undefined>(undefined)

    useEffect(() => {
        setDescription(event?.description);
        setSummary(event?.summary)
        setEventStartTime(dayjs(event?.start.dateTime.value))
        setEventEndTime(dayjs(event?.end.dateTime.value))
    }, [])

    return (
        <div>
            <h2>Event Details</h2>
            <form>
                <Input placeholder={"Summary"} onChange={e => setSummary(e.target.value)} value={summary}/>
                <DateTimePicker label={"Start Time"} setTime={setEventStartTime} time={eventStartTime}/>
                <DateTimePicker label={"End Time"} setTime={setEventEndTime} time={eventEndTime}/>
                <Textarea placeholder={"Description"} onChange={e => setDescription(e.target.value)}
                          value={description}/>
                <Button type="submit">Add</Button>
            </form>
        </div>
    );
};
