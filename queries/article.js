/**
 * @param {array<string>} hints.tags
 * @param {string} hints.title
 * @param {string} hints.description
 * @return {string}
 * */
function makeQuery(hints) {
    return "From the hints below, generate a 1500 to 3000 words article.\n" +
        "Hints:\n" +
        `Title: ${hints.title}\n` +
        `Description: ${hints.description}\n` +
        `Tags: ${hints.tags.join(",")}`
}