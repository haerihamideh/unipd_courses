import storageUrls from './urls/storageUrls.js';
import receiptUrls from "./urls/receiptUrls.js";
import drugUrls from "./urls/drugUrls.js";
import materialUrls from "./urls/materialUrls.js";
import orderUrls from "./urls/orderUrls.js";
import supplierUrls from "./urls/supplierUrls.js";
import userUrls from "./urls/userUrls.js";
import { getPageTitle } from "./utils.js"
import '../js/table/fancyTable.js'


//routing
$(window).on('hashchange load', function () {
    // document.getElementById("hidden-content").innerHTML = document.title;

    localStorage.removeItem('getData'); // removing all dats before finding the new Data.

    const route = location.hash.replace("#", "").split("/")[0];
    document.title = getPageTitle();


    let endPoint = null;
    const router = new Router();

    switch (route) {
        //TODO: use the fetched data in each case if you need to change the content
        case "receipt":
            //TODO: url must change
            endPoint = receiptUrls.GET_ALL;
            break;
        case "drug":
            endPoint = drugUrls.GET_ALL;
            break;
        case "material":
            endPoint = materialUrls.GET_ALL;
            break;
        case "storage":
            endPoint = storageUrls.GET_ALL;
            break;
        case "order":
            endPoint = orderUrls.GET_ALL;
            break;
        case "supplier":
            endPoint = supplierUrls.GET_ALL;
            break;
        case "report":
            //TODO: url must change ( we dont have urls for report)
            endPoint = receiptUrls.GET_BY_ID;
            break;
        case "user":
            endPoint = userUrls.GET_ALL;
            break;
        case "order_report":
            console.log("A")
            endPoint = orderUrls.GET_ALL;
            break;
        case "receipt_report":
            console.log("B")
            endPoint = receiptUrls.GET_ALL;
        default:
            // endPoint = storageUrls.GET_ALL;
            break;
    }

    const fetch = router.createFetch(endPoint);
    fetch.then(data => {
        localStorage.setItem('getData', JSON.stringify(data));
        // Do something with the data here
    }).catch(error => {
        console.log("Error fetching data:", error);
    });

    // Skip if no page is selected
    if (!route || route === "") return;

    const url = `${route}.html?`;
    $("#main-content").load(url, function (response, status, xhr) {
        if (status == "success") {
            console.log("Content loaded successfully");
        } else {
            console.log("Error loading content: " + xhr.status + " " + xhr.statusText);
        }
    });
});


$(document).ready(function () {
    $("#sidebar-html").load("sidebar.html");
});

