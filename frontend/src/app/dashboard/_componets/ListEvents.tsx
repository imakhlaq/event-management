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
    let hours;
    let remainingMinutes
    if (summary) {
        const minutes = +summary;
        hours = Math.floor(minutes / 60); // Whole hours
        remainingMinutes = minutes % 60; // Remaining minutes
    }

    return (
        <Table className="mt-20">
            <TableCaption>
                <div className="flex justify-around mt-9 text-xl">
                    <div>{content}</div>
                    {summary &&
                        <div><p><span>{hours}H</span> <span>{remainingMinutes && remainingMinutes.toFixed(0)}M</span>
                        </p></div>}
                </div>
            </TableCaption>

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
