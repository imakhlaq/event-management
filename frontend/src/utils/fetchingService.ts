import {service} from "@/utils/service";
import {CalenderEvents} from "../../types";

let url = process.env.NEXT_PUBLIC_BASEURL

export async function fetchEvents(month?: string, year?: string) {

    url = url + "/apiv1/events/get-events";
    if (url === undefined) return;
    if (month !== undefined && year !== undefined) url = `${url}?year=${year}&month=${month}`
    return await service.get<CalenderEvents>(url);
}

export async function fetchEeventById(id: string) {

    url = url + `/apiv1/events/event-by-id/${id}`
    return await service.get<CalenderEvents>(url);
}