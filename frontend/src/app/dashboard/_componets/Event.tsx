"use client";

import {CalenderEvent} from "../../../../types";
import {TableCell, TableRow} from "@/components/ui/table";
import {dataTimeFormatter} from "@/utils/dataTimeFormatter";
import Link from "next/link";

type Props = { calendarEvent: CalenderEvent, };

export default function OneEvent({calendarEvent}: Props) {
    return (
        <TableRow>
            <Link href={`/edit-event/${calendarEvent.id}`}>
                <TableCell className="font-medium">{calendarEvent.summary}</TableCell>
            </Link>
            <TableCell>{dataTimeFormatter(calendarEvent.start.dateTime.value)}</TableCell>
            <TableCell>{dataTimeFormatter(calendarEvent.end.dateTime.value)}</TableCell>
            <TableCell className="text-right">$250.00</TableCell>
        </TableRow>
    )
};
