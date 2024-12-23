import {CalenderEvents} from "../../../../types";
import OneEvent from "@/app/dashboard/_componets/Event";
import {
    Table,
    TableBody,
    TableCaption,
    TableHead,
    TableHeader,
    TableRow,
} from "@/components/ui/table"

type Props = {
    calendarEvents: CalenderEvents,
    summary?: string
};
export default function ListEvents({calendarEvents, summary}: Props) {

    const content = summary === undefined ? "A list of your Events." : "This week summary";

    return (
        <Table>
            <TableCaption>{content}</TableCaption>
            {summary && <TableCaption>{summary}</TableCaption>}
            <TableHeader>
                <TableRow>
                    <TableHead className="w-[15rem]">Summary</TableHead>
                    <TableHead>Created At</TableHead>
                    <TableHead>Ended At</TableHead>
                    <TableHead className="text-right">Delete</TableHead>
                </TableRow>
            </TableHeader>
            <TableBody>
                {calendarEvents.map(event => (
                    <OneEvent key={event.id} calendarEvent={event}/>
                ))}
            </TableBody>
        </Table>

    );
};
