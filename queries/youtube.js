/**
 * @param {string} hints.videoTitle
 * @param {string} hints.author:
 * @param {string} hints.commentTone
 * @param {string} hints.commentDescription
 * @return {string}
 * */
function makeQuery(hints) {
    return "From the hints below, write a comment for a YouTube video.\n" +
        "Hints:\n" +
        `Video Title: ${hints.videoTitle}\n` +
        `Video Author: ${hints.author}\n` +
        `Comment Tone: ${hints.commentTone}\n` +
        `Comment Description: ${hints.commentDescription}`
}