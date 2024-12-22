"use client"
import {fetchEeventById} from "@/utils/fetchingService";
import {useEffect, useState} from "react";
import EventEditor from "@/app/edit-event/[id]/_components/EventEditor";
import {CalenderEvent} from "../../../../types";

type Props = {
    params: Promise<{ id: string }>;
};
export default function Page({params}: Props) {

    const [eventDetails, setEventDetails] = useState<CalenderEvent | undefined>(undefined);

    useEffect(() => {
        (async function () {
            const data = await fetchEeventById((await params).id);
            setEventDetails(data?.data);
        })()
    }, [])

    return (
        <div>
            {eventDetails === undefined ? null : <EventEditor event={eventDetails}/>}
        </div>
    );
};
