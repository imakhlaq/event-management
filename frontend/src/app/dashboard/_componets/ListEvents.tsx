import {CalenderEvents} from "../../../../types";
import OneEvent from "@/app/dashboard/_componets/Event";
import {
    Table,
    TableBody,
    TableCaption,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
} from "@/components/ui/table"

type Props = {
    calendarEvents: CalenderEvents
};
export default function ListEvents({calendarEvents}: Props) {
    return (
        <Table>
            <TableCaption>A list of your Events.</TableCaption>
            <TableHeader>
                <TableRow>
                    <TableHead className="w-[15rem]">Summary</TableHead>
                    <TableHead>Created At</TableHead>
                    <TableHead>Ended At</TableHead>
                    <TableHead className="text-right">Amount</TableHead>
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
