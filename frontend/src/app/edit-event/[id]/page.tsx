"use client"
import {useEffect, useState} from "react";
import EventEditor from "@/app/edit-event/[id]/_components/EventEditor";
import {CalenderEvent} from "../../../../types";
import {fetchEventById} from "@/utils/fetchingService";

type Props = {
    params: Promise<{ id: string }>;
};
export default function Page({params}: Props) {

    const [eventDetails, setEventDetails] = useState<CalenderEvent | undefined>(undefined);

    useEffect(() => {
        (async function () {
            const data = await fetchEventById((await params).id);
            setEventDetails(data?.data);
        })()
    }, [])

    return (
        <div>
            {eventDetails === undefined ? null : <EventEditor event={eventDetails}/>}
        </div>
    );
};
