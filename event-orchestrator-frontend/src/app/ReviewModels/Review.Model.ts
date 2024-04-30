export class Review {
    reviewID?: number; 
    userID: string;
    eventID: number;
    rating: number;
    content: string;
    dateSubmitted: string;

    constructor(
        userID: string,
        eventID: number,
        rating: number,
        content: string,
        dateSubmitted: string
    ) {
        this.userID = userID;
        this.eventID = eventID;
        this.rating = rating;
        this.content = content;
        this.dateSubmitted = dateSubmitted;
    }
}