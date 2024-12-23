"use client"
import {CalenderEvent, EventData} from "../../../../../types";
import {Input} from "@/components/ui/input";
import DateTimePicker from "@/app/edit-event/[id]/_components/DateTimePicker";
import {Textarea} from "@/components/ui/textarea";
import {Button} from "@/components/ui/button";
import {FormEvent, useEffect, useState} from "react";
import dayjs, {Dayjs} from "dayjs";
import {createEvent, updateEvent} from "@/utils/fetchingService";

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

    async function handleSubmit(e: FormEvent<HTMLFormElement>) {
        e.preventDefault();

        if (
            summary === undefined &&
            description === undefined &&
            eventStartTime === null &&
            eventEndTime === null
        ) return;

        //"yyyy-MM-dd'T'HH:mm:ss"
        const data: EventData = {
            summary,
            description,
            location: event?.location,
            eventType: event?.eventType,
            startTime: eventStartTime?.format("YYYY-MM-DDTHH:mm:ss"),
            endTime: eventEndTime?.format("YYYY-MM-DDTHH:mm:ss")
        }

        if (event?.id) {
            //handle Update
            data.id = event.id;
            data.location = "allahabad";
            const res = await updateEvent(data);
            console.log(res.data)
            return
        }
        //handle create
        const res = await createEvent(data)
        console.log(res.data)
        return
    }

    return (
        <div className="container flex h-screen flex-col justify-center items-center py-6 gap-10">
            <h2 className="text-2xl">Event Details</h2>
            <form onSubmit={handleSubmit} className="flex flex-col gap-10">
                <div>
                    <label>Summary</label>
                    <Input placeholder={"Summary"} onChange={e => setSummary(e.target.value)} value={summary}/>
                </div>
                <DateTimePicker label={"Start Time"} setTime={setEventStartTime} time={eventStartTime}/>
                <DateTimePicker label={"End Time"} setTime={setEventEndTime} time={eventEndTime}/>
                <div>
                    <label>Description</label>
                    <Textarea placeholder={"Description"} onChange={e => setDescription(e.target.value)}
                              value={description}/>
                </div>
                <Button type="submit">{event?.id !== undefined ? "Update" : "Add"}</Button>
            </form>
        </div>
    );
};
