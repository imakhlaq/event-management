"use client";

import {CalenderEvent} from "../../../../types";
import {TableCell, TableRow} from "@/components/ui/table";
import {dataTimeFormatter} from "@/utils/dataTimeFormatter";
import Link from "next/link";
import {Button} from "@/components/ui/button";
import {MdDeleteForever} from "react-icons/md";
import {deleteEventById} from "@/utils/fetchingService";
import {useToast} from "@/hooks/use-toast";
import {useRouter} from "next/navigation";

type Props = { calendarEvent: CalenderEvent };

export default function OneEvent({calendarEvent}: Props) {

    const {toast} = useToast()
    const router = useRouter()

    async function deleteHandler() {
        try {
            const response = await deleteEventById(calendarEvent.id);
            toast({
                title: "Event Deleted",
                description: response.data.message
            })
        } catch (e) {
            toast({
                title: "Event Delete Failed",
                // eslint-disable-next-line @typescript-eslint/ban-ts-comment
                // @ts-expect-error
                description: e.message
            });
        } finally {
            router.push("/dashboard")
        }
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
