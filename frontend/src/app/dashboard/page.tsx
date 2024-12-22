"use client";

import {useEffect, useState} from "react";
import {CalenderEvents} from "../../../types";
import ListEvents from "@/app/dashboard/_componets/ListEvents";
import {fetchEvents} from "@/utils/fetchingService";

export default function Page() {

    const [data, setData] = useState<CalenderEvents | undefined>(undefined)

    useEffect(() => {
        (async function () {
            const data = await fetchEvents()
                .catch(err => console.log(err))
            setData(data?.data)

            console.log(data?.data)

        }())
    }, [])
    return (
        <>
            <div>ALL Events</div>
            {data === undefined ? null : <ListEvents calendarEvents={data}/>}
        </>
    );
};
