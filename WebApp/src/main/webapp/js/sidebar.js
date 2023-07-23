import receiptUrls from "./urls/receiptUrls";
import drugUrls from "./urls/drugUrls";
import materialUrls from "./urls/materialUrls";
import storageUrls from "./urls/storageUrls";
import orderUrls from "./urls/orderUrls";
import supplierUrls from "./urls/supplierUrls";
import userUrls from "./urls/userUrls";

$(function () {


    // TODO : we must call the api like this example ->

    // const data = router.createFetch(storageUrls.GET_ALL);
    // console.log(data);
    // Loop through each <li> element and add a click event listener to it
    const listItems = document.querySelectorAll('li');
    let selectedItem = null;
    let fetchedData = null;
    listItems.forEach(item => {
        item.addEventListener('click', async () => {
            if (selectedItem) {
                selectedItem.style.backgroundColor = '#F2F9F9ED';
            }
            item.style.backgroundColor = '#DEF3EE';
            selectedItem = item;
            const router = new Router();
            //TODO: Need to fix the url for each page
            switch (item.id) {
                //TODO: use the fetched data in each case if you need to change the content
                case "receipt":
                    //TODO: url must change
                    fetchedData = router.createFetch(receiptUrls.GET_BY_ID)
                    break;
                case "drug":
                    fetchedData = router.createFetch(drugUrls.GET_ALL)
                    break;
                case "material":
                    fetchedData = router.createFetch(materialUrls.GET_ALL)
                    break;
                case "storage":
                    fetchedData = router.createFetch(storageUrls.GET_ALL)
                    break;
                case "order":
                    fetchedData = router.createFetch(orderUrls.GET_ALL)
                    break;
                case "supplier":
                    fetchedData = router.createFetch(supplierUrls.GET_ALL)
                    break;
                case "report":
                    //TODO: url must change ( we dont have urls for report)
                    fetchedData = router.createFetch(receiptUrls.GET_BY_ID)
                    break;
                case "user":
                    fetchedData = router.createFetch(userUrls.GET_BY_ID)
                    break;
                case "setting":
                    //TODO: url must change
                    // the data for setting page is static?!
                    break;
                default:
                    break;
            }
            // TODO: Do something when the <li> element is clicked, such as navigate to a new page or show/hide content
            // console.log('You clicked on an <li> element!');
        });
    });
});

