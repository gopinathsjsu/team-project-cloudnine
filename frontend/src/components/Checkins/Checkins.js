import React, { useEffect, useContext, useState } from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { Button, IconButton } from '@mui/material';
import Checkbox from '@mui/material/Checkbox';
import { List, ListItem, ListItemText, Divider, ListItemButton, ListItemIcon } from '@mui/material';
import { style } from '@mui/material';
import { Add, Comment } from '@mui/icons-material';

import { Grid, Container } from '@mui/material';

import './Checkins.scss';
import ProfileHeader from 'components/ProfileHeader/ProfileHeader';
import util from 'util';

function Checkins(props) {
  const [checked, setChecked] = useState([false, false, false, false]);

  const handleToggle = (i) => {
    checked[i] = !checked[i];
    setChecked(checked.slice(0, checked.length));
  };

  return (
    <div className="component-weekly-schedule">

      {/* header */}
      <ProfileHeader layout={[0, 0]} noAction title="Visits" />

      <Card>
        <CardContent>

          <List className='my-list' component="nav" aria-label="mailbox folders">
            {props.data.map(dataCheckinCheckout =>
              <ListItem>
                <ListItemText primary={`${util.timeify(dataCheckinCheckout.checkInTime)}`} secondary={util.dateify(dataCheckinCheckout.checkInTime)} />
              </ListItem>
            )}
          </List>

        </CardContent>
      </Card>

    </div>
  );
}

export default Checkins;
