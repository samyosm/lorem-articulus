/**
 * @param {array<string>} hints.topics
 * @param {string} hints.tone:
 * @param {string} hints.description
 * @return {string}
 * */
function makeQuery(hints) {
    return "From the hints below, write a Twitter post.\n" +
        "Hints:\n" +
        `Topics: ${hints.topics.join(",")}\n` +
        `Tone: ${hints.tone}\n` +
        `Description: ${hints.description}`
}