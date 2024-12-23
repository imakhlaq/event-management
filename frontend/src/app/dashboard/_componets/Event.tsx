"use client";

import {CalenderEvent} from "../../../../types";
import {TableCell, TableRow} from "@/components/ui/table";
import {dataTimeFormatter} from "@/utils/dataTimeFormatter";
import Link from "next/link";
import {Button} from "@/components/ui/button";
import {MdDeleteForever} from "react-icons/md";
import {deleteEventById} from "@/utils/fetchingService";

type Props = { calendarEvent: CalenderEvent };

export default function OneEvent({calendarEvent}: Props) {
    async function deleteHandler() {
        const response = await deleteEventById(calendarEvent.id);
        console.log(response.data);
    }

    const startTime =
        calendarEvent.start.dateTime !== undefined
            ? dataTimeFormatter(calendarEvent.start.dateTime.value)
            : "";
    const endTime =
        calendarEvent.start.dateTime !== undefined
            ? dataTimeFormatter(calendarEvent.end.dateTime.value)
            : "";

    return (
        <TableRow>
            <TableCell className="font-medium">
                {" "}
                <Link href={`/edit-event/${calendarEvent.id}`}>
                    {calendarEvent.summary}{" "}
                </Link>
            </TableCell>
            <TableCell>{startTime}</TableCell>
            <TableCell>{endTime}</TableCell>
            <TableCell className="text-right">
                <Button className={"bg-red-400"} onClick={deleteHandler}>
                    <MdDeleteForever/>
                </Button>
            </TableCell>
        </TableRow>

    );
}
