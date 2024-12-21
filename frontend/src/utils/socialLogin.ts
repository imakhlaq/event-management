export function googleLogin() {
    if (window === undefined) return;
    window.location.href = "http://localhost:8080/oauth2/authorization/google"
}