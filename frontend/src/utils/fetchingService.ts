import {service} from "@/utils/service";
import {CalenderEvents} from "../../types";

const url = process.env.NEXT_PUBLIC_BASEURL

export async function fetchEvents(month?: string, year?: string) {

    let allEvents = url + "/apiv1/events/get-events";
    if (url === undefined) return;
    if (month !== undefined && year !== undefined) allEvents = `${url}?year=${year}&month=${month}`
    return await service.get<CalenderEvents>(allEvents);
}

export async function fetchEeventById(id: string) {

    const oneEventByIDURI = url + `/apiv1/events/event-by-id/${id}`
    return await service.get<CalenderEvent>(oneEventByIDURI);
}