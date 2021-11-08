import { IPost } from "./post";
import { IUser } from "./user";

export interface IComment{
    asnCommentId? : number;
    asnCommentDescription: string;
    asnPostFK: IPost;
    asnUserFK: IUser;

}