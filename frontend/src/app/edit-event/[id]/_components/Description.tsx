"use client";

import {Textarea} from "@/components/ui/textarea";

type Props = {
    description?: string
};
export default function Description({description}: Props) {
    return (
        <Textarea value={description}/>
    );
};
