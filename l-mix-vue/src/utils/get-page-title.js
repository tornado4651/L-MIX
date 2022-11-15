import defaultSettings from '@/settings'

const title = defaultSettings.title || 'L-MIX'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
