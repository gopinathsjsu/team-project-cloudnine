import React, { useEffect, useContext, useState } from 'react';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';

import AppContext from 'context';

function Toast(props) {
  // const [open, setOpen] = useState(false);
  const { context, setContext } = useContext(AppContext);

  // -----------

  const handleClose = () => {
    setContext({
      ...context,
      toast: null,
    })
  }

  return (
    <div className="page-toast">
      {context.toast != null ?
        <Snackbar open={context.toast != null} autoHideDuration={3000} onClose={handleClose} anchorOrigin={{ vertical: 'bottom', horizontal: 'bottom' }}>
          <Alert onClose={handleClose} severity={context.toast.severity} variant="filled">
            {context.toast.message}
          </Alert>
        </Snackbar>
        : ''
      }
    </div>
  );
}

export default Toast;
