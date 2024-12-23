"use client";

import {useEffect, useState} from "react";
import {CalenderEvents} from "../../../types";
import ListEvents from "@/app/dashboard/_componets/ListEvents";
import {fetchEvents} from "@/utils/fetchingService";
import {Combobox} from "@/app/dashboard/_componets/ChooseMonth";
import {Button} from "@/components/ui/button";
import Link from "next/link";

export default function Page() {
    const [data, setData] = useState<CalenderEvents | undefined>(undefined);
    const [month, setMonth] = useState<string | undefined>(undefined);

    useEffect(() => {
        (async function () {
            const data = await fetchEvents(month, "2024").catch((err) =>
                console.log(err),
            );
            setData(data?.data);
        })();
    }, [month]);

    console.log(`Month selected ${month}`);
    return (
        <div className="container mx-auto w-screen flex justify-center flex-col">
            <div className="flex items-center gap-6">
                <div className="text-3xl p-3">ALL Events</div>
                <Combobox setMonth={setMonth}/>
                <Button>
                    <Link href={"/create-event"}>Create Event</Link>
                </Button>
                <Button>
                    <Link href={"/week-summary"}>Week Summary</Link>
                </Button>
            </div>
            {data === undefined ? null : <ListEvents calendarEvents={data}/>}
        </div>
    );
}







