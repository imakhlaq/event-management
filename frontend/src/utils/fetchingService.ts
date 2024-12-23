import {service} from "@/utils/service";
import {CalenderEvent, CalenderEvents, DeleteEventRes, EventData} from "../../types";

const url = process.env.NEXT_PUBLIC_BASEURL

export async function fetchEvents(month?: string, year?: string) {

    let allEvents = url + "/apiv1/events/get-events";
    if (month !== undefined && year !== undefined) allEvents += `?year=${year}&month=${month}`
    console.log(allEvents)
    return await service.get<CalenderEvents>(allEvents);
}

export async function fetchEventById(id: string) {

    const oneEventByIDURI = url + `/apiv1/events/event-by-id/${id}`
    return await service.get<CalenderEvent>(oneEventByIDURI);
}

export async function createEvent(data: EventData) {

    const createEventURI = url + `/apiv1/events/add-events`
    return await service.post<CalenderEvent>(createEventURI, data);
}

export async function updateEvent(data: EventData) {

    const updateEventURI = url + `/apiv1/events/update-event`
    return await service.patch<CalenderEvent>(updateEventURI, data);
}

export async function deleteEventById(id: string) {

    const deleteEventByIDURI = url + `/apiv1/events/delete/${id}`
    return await service.delete<DeleteEventRes>(deleteEventByIDURI);
}