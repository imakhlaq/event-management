"use client";

import {useEffect, useState} from "react";
import {WeekSummary} from "../../../types";
import {fetchThisWeekSummary} from "@/utils/fetchingService";
import ListEvents from "@/app/dashboard/_componets/ListEvents";

export default function Page() {

    const [weekSummary, setWeekSummary] = useState<WeekSummary | undefined>(undefined);

    useEffect(() => {
        (async function () {
            const data = await fetchThisWeekSummary();
            setWeekSummary(data.data)
        })()
    }, [])

    return (
        <div className="container mx-auto w-screen flex justify-center">
            {weekSummary === undefined ? null :
                <ListEvents calendarEvents={weekSummary.allEventsThisWeek} summary={weekSummary.totalNumberOfHours}/>}
        </div>
    );
};
