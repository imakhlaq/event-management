"use client";

import {CalenderEvent} from "../../../../types";
import {TableCell, TableRow} from "@/components/ui/table";
import {dataTimeFormatter} from "@/utils/dataTimeFormatter";
import Link from "next/link";
import {Button} from "@/components/ui/button";
import {MdDeleteForever} from "react-icons/md";
import {deleteEventById} from "@/utils/fetchingService";

type Props = { calendarEvent: CalenderEvent, };

export default function OneEvent({calendarEvent}: Props) {

    async function deleteHandler() {
        const response = await deleteEventById(calendarEvent.id)
        console.log(response.data)
    }

    return (
        <TableRow>
            <Link href={`/edit-event/${calendarEvent.id}`}>
                <TableCell className="font-medium">{calendarEvent.summary}</TableCell>
            </Link>
            <TableCell>{dataTimeFormatter(calendarEvent.start.dateTime.value)}</TableCell>
            <TableCell>{dataTimeFormatter(calendarEvent.end.dateTime.value)}</TableCell>
            <TableCell className="text-right"><Button className={"bg-red-400"}
                                                      onClick={deleteHandler}><MdDeleteForever/>
            </Button></TableCell>
        </TableRow>
    )
};
