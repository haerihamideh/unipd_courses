// modal.js


// Example data for the table


// Function to generate the table rows dynamically


export const createModal = (title, content, type, data, footerContent) => {

    const modalEl = document.createElement('div');
    modalEl.classList.add('modal', 'fade');
    modalEl.setAttribute('id', 'errorModal');
    modalEl.setAttribute('tabindex', '-1');
    modalEl.setAttribute('role', 'dialog');
    modalEl.setAttribute('aria-labelledby', 'errorModalLabel');
    modalEl.setAttribute('aria-hidden', 'true');

    switch (type) {
        case 'Error':
            modalEl.innerHTML = `
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header bg-danger text-white">
          <h5 class="modal-title" id="errorModalLabel">${title}</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body text-danger">
          ${content}
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">OK</button>
        </div>
      </div>
    </div>
  `;
            break;
        case 'Receipt_submit':
            const generateTableRows = (data) => {
                return data.map(item => `
    <tr>
      <td><input type="checkbox" name="item" value="${item.id}" checked disabled></td>
      <td>${item.name}</td>
      <td>${item.age}</td>
      <td>${item.city}</td>
    </tr>
  `).join('');
            };
            const tableRows = generateTableRows(data);
            modalEl.innerHTML = `
    <div class="modal-dialog" role="document" style="max-width: 60%">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="errorModalLabel">${title}</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-8">
              <table class="table">
                <thead>
                  <tr>
                    <th></th>
                    <th>Product Name</th>
                    <th>Age Group</th>
                    <th>Price</th>
                  </tr>
                </thead>
                <tbody>
                  ${tableRows}
                </tbody>
              </table>
            </div>
            <div class="col-4">
              <img src="../images/Logo_padova.png" alt="Image" class="img-fluid">
            </div>
          </div>
        </div>
        <div class="modal-footer d-flex justify-content-between">
          <h6 class="text-muted">${footerContent}</h6>
          <div class="d-flex">
            <button type="button" class="btn btn-secondary mr-2" data-dismiss="modal">Cancel</button>
            <button type="button" class="btn btn-primary btn-custom">Accept</button>
          </div>
        </div>
      </div>
    </div>
  `;
            break;
        case 'Order_submit':
            const price = data.price;//"$12000";
            const tax = data.tax;//"10%";
            const discount = data.discount;//"12%";
            const total = calculateTotalPrice(price, tax, discount); // Calculate the total price

            modalEl.innerHTML = `
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="errorModalLabel">${title}</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
        <p><strong>${content}</strong></p>
            <div class="modal_content-box">
                <p>Price: ${price}</p>
                <p>Tax: ${tax}</p>
                <p>Discount: ${discount}</p>
                <hr>
                <p>Total price: ${total}</p>
            </div>
        </div>
        <div class="modal-footer justify-content-center">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
          <button type="button" class="btn btn-primary">Submit Order</button>
        </div>
      </div>
    </div>
  `;
            break;
        default:
            modalEl.innerHTML = `
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="errorModalLabel">${title}</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          ${content}
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  `;
            break;
    }


    return modalEl;
};


export const showModal = (title, content, type, data, footerContent) => {
    const modalEl = createModal(title, content, type, data, footerContent);
    document.body.appendChild(modalEl);

    // Get the main content container
    const mainContent = document.getElementById('main-container');

    // Add the blur effect to the main content container
    mainContent.classList.add('blur-effect');

    // Add classes to body element to prevent scrolling and apply modal styles
    document.body.classList.add('modal-open');
    $(modalEl).modal('show');

    // Remove modal and blur effect when modal is closed
    $(modalEl).on('hidden.bs.modal', () => {
        document.body.removeChild(modalEl);
        mainContent.classList.remove('blur-effect');
        document.body.classList.remove('modal-open');
    });
};

// Function to calculate the total price based on price, tax, and discount
const calculateTotalPrice = (price, tax, discount) => {
    const numericPrice = parseFloat(price.replace('$', ''));
    const numericTax = parseFloat(tax.replace('%', ''));
    const numericDiscount = parseFloat(discount.replace('%', ''));

    const total = numericPrice * (1 + numericTax / 100) * (1 - numericDiscount / 100);
    return `$${total.toFixed(2)}`;
};