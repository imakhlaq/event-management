"use client";

import axios from "axios";
import {useEffect, useState} from "react";
import {CalenderEvents} from "../../../types";
import ListEvents from "@/app/dashboard/_componets/ListEvents";

export default function Page() {

    const [data, setData] = useState<CalenderEvents | undefined>(undefined)

    useEffect(() => {
        (async function () {
            const data = await axios.get<CalenderEvents>("http://localhost:8080/apiv1/events/get-events", {withCredentials: true})
                .catch(err => console.log(err))
            setData(data?.data)
        }())
    }, [])
    return (
        <>
            <div>Dashboard</div>
            {data === undefined ? null : <ListEvents calendarEvents={data}/>}
        </>
    );
};
