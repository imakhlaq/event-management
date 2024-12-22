export function dataTimeFormatter(epliseTime: number) {

    const dateTime = new Date(epliseTime);

    const formattedDate = new Intl.DateTimeFormat('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
        second: 'numeric'
    }).format(dateTime);
    return formattedDate;
}