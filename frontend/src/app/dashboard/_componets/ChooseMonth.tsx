"use client"

import * as React from "react"
import {Check, ChevronsUpDown} from "lucide-react"

import {cn} from "@/lib/utils"
import {Button} from "@/components/ui/button"
import {
    Command,
    CommandEmpty,
    CommandGroup,
    CommandInput,
    CommandItem,
    CommandList,
} from "@/components/ui/command"
import {
    Popover,
    PopoverContent,
    PopoverTrigger,
} from "@/components/ui/popover"
import {Dispatch, SetStateAction} from "react";

const frameworks = [
    {
        value: "1",
        label: "January"
    },
    {
        value: "2",
        label: "February",
    },
    {
        value: "3",
        label: "March",
    },
    {
        value: "4",
        label: "April",
    },
    {
        value: "5",
        label: "May",
    },
    {
        value: "6",
        label: "June",
    },
    {
        value: "7",
        label: "July",
    },
    {
        value: "8",
        label: "August",
    },
    {
        value: "9",
        label: "September",
    }, {
        value: "10",
        label: "October",
    },
    {
        value: "11",
        label: "November",
    },
    {
        value: "12",
        label: "December",
    },
]

type Props = {
    setMonth: Dispatch<SetStateAction<string | undefined>>
}

export function Combobox({setMonth}: Props) {
    const [open, setOpen] = React.useState(false)
    const [value, setValue] = React.useState("")

    return (
        <Popover open={open} onOpenChange={setOpen}>
            <PopoverTrigger asChild>
                <Button
                    variant="outline"
                    role="combobox"
                    aria-expanded={open}
                    className="w-[200px] justify-between"
                >
                    {value
                        ? frameworks.find((framework) => framework.value === value)?.label
                        : "Select Month..."}
                    <ChevronsUpDown className="opacity-50"/>
                </Button>
            </PopoverTrigger>
            <PopoverContent className="w-[200px] p-0">
                <Command>
                    <CommandInput placeholder="Search framework..." className="h-9"/>
                    <CommandList>
                        <CommandEmpty>No framework found.</CommandEmpty>
                        <CommandGroup>
                            {frameworks.map((framework) => (
                                <CommandItem
                                    key={framework.value}
                                    value={framework.value}
                                    onSelect={(currentValue) => {
                                        setValue(currentValue === value ? "" : currentValue)
                                        setMonth(currentValue === value ? "" : currentValue);
                                        setOpen(false)
                                    }}
                                >
                                    {framework.label}
                                    <Check
                                        className={cn(
                                            "ml-auto",
                                            value === framework.value ? "opacity-100" : "opacity-0"
                                        )}
                                    />
                                </CommandItem>
                            ))}
                        </CommandGroup>
                    </CommandList>
                </Command>
            </PopoverContent>
        </Popover>
    )
}
