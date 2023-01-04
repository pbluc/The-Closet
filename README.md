The Closet
===

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
A virtual closet app for your real-life wardrobe, with tools and features to manage everything from your favorite outfits to your sizes and style inspirations.

### App Evaluation

- **Category:** Wardrobe Organizational, Closet Management Tool, Photo / Video
- **Mobile:** Uses camera, mobile first experience
- **Story:** Allows the user to take control of their wardrobe. Get the most out of what you own in your closet.
- **Market:** Anyone wanting to know more about their wardrobe and better their own personal style.
- **Habit:** Users can routinely micro-manage their closet and enhance their closet control.
- **Scope:** Primarily used by fashion-centered individuals interested in improving their wardrobe capabilities.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**
* User can login and logout
* User can create a new account
#### Closet
* User can add and delete closet items, outfits, and folders
* User can edit outfits and folder names
* User can see all items in a category or folder at once
* User can select multiple closet items to move to a new category
* User can select multiple outfits in a folder to move to a new folder
* User can add photos of their own clothes from the built-in camera or photo library
* User can track whether an item is clean in the laundry, at the cleaners, lent out, or a wishlist item
* User can tap on a closet item to zoom in on its full view
#### Outfits
* User can remove the background from clothing images upon import for perfect layering in outfits
* User can create magazine-style outfits on a free-form canvas by pinching and dragging clothing items to create a new outfit
    * User can pinch an item to resize it
    * User can drag an item to move across canvas and reposition
* User can see if an outfit they're putting together has been repeated
* User can use the shuffle feature to shuffle their closet like a deck of cards and reveal new outfit ideas in your closet
* User can create variants on an existing outfit by cloning it and then making changes to the cloned outfit
* User can create mini mood boards by saving inspiration photos in their outfit
* User can save notes to outfits on upcoming parties and events, or remember why they wore an outfit in the past
#### Calendar
* User can add multiple outfits or clothing items to a single date in calendar
* User can re-order items within a day
* User can track what they wore through calendar
* User can switch from a monthly to weekly or daily overview of outfit(s)
* User can view past outfits worn all at once
#### Size Tracker
* User can save their body measurements e.g. waist, inseam, hips, etc
* User can edit their body measurements
#### Packing Lists
* User can create a visual packing list for upcoming trips by organizing outfits and clothing items into packing lists
* User can add, delete and rename packing lists
* User can delete items from packing lists
* User can check off items as they pack them
* User can add an outfit to a packing list, and its clothing items will automatically be added to the list
* User can add individual clothing items to packing list


**Optional Nice-to-have Stories**
* User can generate a worn history log for clothing and outfits
#### Social Media and Sharing
* User can share outfits with friends via email, text message, Facebook, Twitter or Pinterest
* User can use Wifi Share to instantly transit mutliple outfits to another device
#### Packing Lists
* User can re-order their packing lists
* User can keep an archive of packing lists
* User can customize notes fields in packing lists
* User can share clothing or outfits directly from packing list by multi-selecting to send several items at once via email or social media
* User can assign an image of their destination to each packing list
#### Statistics
* User can see the total number of items in their closet
* User can find out which items in their closet are under-utilized or may need to be donated
* User can see items they haven't worn lately to freshen up their wardrobe without going shopping
* User can see which outfits and clothing items they wear the most - and which ones they rarely wear.
* User can see the items they've added to their closet most recently
* A color bar to break down the user's closet by color - can tap on a color to see individual shades

### 2. Screen Archetypes
* Login Screen
   * User can login/logout
* Create Account Screen
   * User can create a new account
* Closet Screen (Home/Main Screen)
    * User can add and delete closet items, outfits, and folders
    * User can see all items in a category or folder at once
    * User can select multiple closet items to move to a new category
    * User can add photos of their own clothes from the built-in camera or photo library
    * User can track whether an item is clean in the laundry, at the cleaners, lent out, or a wishlist item
    * User can tap on a closet item to zoom in on its full view
* Import Closet Item Screen
    * User can remove the background from clothing images upon import for perfect layering in outfits
* Outfits Screen
    * User can add and delete closet items, outfits, and folders
    * User can edit outfits and folder names
    * User can see all items in a category or folder at once
    * User can select multiple outfits in a folder to move to a new folder
    * User can create variants on an existing outfit by cloning it and then making changes to the cloned outfit
* Edit Outfit Screen
    * User can create magazine-style outfits on a free-form canvas by pinching and dragging clothing items to create a new outfit
        * User can pinch an item to resize it
        * User can drag an item to move across canvas and reposition
    * User can use the shuffle feature to shuffle their closet like a deck of cards and reveal new outfit ideas in your closet
    * User can create mini mood boards by saving inspiration photos in their outfit
    * User can save notes to outfits on upcoming parties and events, or remember why they wore an outfit in the past
    * User can see if an outfit they’re putting together has been repeated
* Packing Lists Screen
    * User can create a visual packing list for upcoming trips by organizing outfits and clothing items into packing lists
    * User can add, delete and rename packing lists
    * User can delete items from packing lists
    * User can check off items as they pack them
    * User can add an outfit to a packing list, and its clothing items will automatically be added to the list
    * User can add individual clothing items to packing list
* Calendar Screen
    * User can add multiple outfits or clothing items to a single date in calendar
    * User can re-order items within a day
    * User can switch from a monthly to weekly or daily overview of outfit(s)
    * User can view past outfits worn all at once
* Measurements Screen
    * User can save their body measurements e.g. waist, inseam, hips, etc
    * User can edit their body measurements

### 3. Navigation

**Tab Navigation** (Tab to Screen)
* Closet
    * Closet Screen (Home/Main Screen)
        * Bottoms (Skirts, Shorts, Pants), Tops (Long Sleeves, Short Sleeves, Tank Tops, Vests, Sweatshirts, Hoodies), One-Pieces, Dresses, Shoes, Underwear, Bags, Hats, Outerwear, Gloves, Scarves, Ties, Swimsuits, Bras
* Outfits
    * Outfits Screen
* Calendar
    * Calendar Screen
* Packing Lists
    * Packing Lists Screen
* Measurements
    * Measurements Screen

**Flow Navigation** (Screen to Screen)
* Login Screen
    * Create Account Screen
    * Closet Screen
* Create Account Screen
    * Login Screen
    * Closet Screen
* Closet Screen
    * Import Closet Item Screen
* Import Closet Item Screen
    * Closet Screen
* Outfits Screen
    * Edit Outfit Screen
* Edit Outfit Screen
    * Outfits Screen
* Packing Lists Screen
    * Outfits Screen
    * Closet Screen
* Calendar Screen
    * Outfits Screen
    * Closet Screen


## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="YOUR_WIREFRAME_IMAGE_URL" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]