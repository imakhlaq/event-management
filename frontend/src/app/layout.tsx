import type {Metadata} from "next";
import "./globals.css";

import {Roboto, Poppins} from 'next/font/google';
import NavBar from "@/app/_components/NavBar";

const poppins = Poppins({
    weight: ["100", "900"],
    subsets: ['latin'],
    variable: '--poppins-default'
});

const roboto = Roboto({
    weight: ["100", "900"],
    subsets: ['latin'],
    variable: '--roboto-default'
});

export const metadata: Metadata = {
    title: "Event Management",
    description: "Generated by create next app",
};

export default function RootLayout({
                                       children,
                                   }: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="en">
        <body
            className={`${roboto.variable} ${poppins.variable} antialiased font-sans bg-background bg-gradient-to-r from-slate-50 to-gray-200 `}
        >
        <NavBar/>
        {children}
        </body>
        </html>
    );
}
