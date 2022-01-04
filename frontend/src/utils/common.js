/**
 * 添加/更新session缓存
 * 对象进行字符串化是为了能存储Object、Array等
 * @param {String} key
 * @param {*} value
 */
export function setSessionStorage(key, value) {
    let formatValue = JSON.stringify(value)
    sessionStorage.setItem(key, formatValue)
}

  
/**
 * 获取session缓存
 * @param {String} key
 * @returns {*}
 */
export function getSessionStorage(key) {
    let value = sessionStorage.getItem(key)
    return JSON.parse(value)
}