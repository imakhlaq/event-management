export type CalenderEvents = CalenderEvent[]

export type CalenderEvent = {
    attendees?: Attendee[]
    created: Created
    creator: Creator
    description?: string
    end: End
    endTimeUnspecified?: boolean
    etag: string
    eventType: string
    guestsCanInviteOthers?: boolean
    htmlLink: string
    iCalUID: string
    id: string
    kind: string
    location?: string
    organizer: Organizer
    reminders: Reminders
    sequence: number
    source?: Source
    start: Start
    status: string
    summary: string
    transparency?: string
    updated: Updated
    visibility?: string
    colorId?: string
    conferenceData?: ConferenceData
    hangoutLink?: string
}

export interface Attendee {
    email: string
    organizer?: boolean
    responseStatus: string
    self?: boolean
}

export interface Created {
    value: number
    dateOnly: boolean
    timeZoneShift: number
}

export interface Creator {
    email: string
    self?: boolean
}

export interface End {
    dateTime: DateTime
    timeZone: string
}

export interface DateTime {
    value: number
    dateOnly: boolean
    timeZoneShift: number
}

export interface Organizer {
    email: string
    self?: boolean
}

export interface Reminders {
    useDefault: boolean
}

export interface Source {
    title: string
    url: string
}

export interface Start {
    dateTime: DateTime2
    timeZone: string
}

export interface DateTime2 {
    value: number
    dateOnly: boolean
    timeZoneShift: number
}

export interface Updated {
    value: number
    dateOnly: boolean
    timeZoneShift: number
}

export interface ConferenceData {
    conferenceId: string
    conferenceSolution: ConferenceSolution
    entryPoints: EntryPoint[]
}

export interface ConferenceSolution {
    iconUri: string
    key: Key
    name: string
}

export interface Key {
    type: string
}

export interface EntryPoint {
    entryPointType: string
    label?: string
    uri: string
    pin?: string
    regionCode?: string
}

export type EventData = {
    id?: string,
    summary?: string,
    description?: string,
    location?: string,
    eventType?: string,
    startTime?: string, //"yyyy-MM-dd'T'HH:mm:ss"
    endTime?: string,

}